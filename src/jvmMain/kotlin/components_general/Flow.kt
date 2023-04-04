package components_general

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*

@Composable
fun HorizontalFlow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable FlowScope.() -> Unit
) {
    Layout(content = { FlowScopeInstance.content() }, modifier) { measurables, constraints ->
        if (measurables.isEmpty()) {
            return@Layout layout(constraints.minWidth, constraints.minHeight) {}
        }


        var currentStripeLength = 0
        var allocatedSpacing = 0
        val horizontalSpacingPx = horizontalArrangement.spacing.roundToPx()
        val verticalSpacingPx = horizontalArrangement.spacing.roundToPx()

        val itemConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val placeables = measurables.map { it.measure(itemConstraints) }
        val stripes = mutableListOf(mutableListOf<Int>())
        placeables.forEachIndexed { index, placeable ->
            if (currentStripeLength + placeable.width > constraints.maxWidth - allocatedSpacing) {
                stripes.add(mutableListOf())
                currentStripeLength = 0
                allocatedSpacing = 0
            }
            val currentStripe = stripes.last()
            currentStripe.add(index)
            if (currentStripe.size > 0) {
                allocatedSpacing += horizontalSpacingPx
            }
            currentStripeLength += placeable.width
        }

        val alignmentLinePositions = measurables.mapIndexed { index, measurable ->
            val parentData = measurable.parentData as? FlowParentData
            parentData?.let { data -> data.alignmentLine?.let { placeables[index][it] } }
        }

        val alignmentLineSizes = stripes.map { it.maxOf { alignmentLinePositions[it] ?: 0 } }
        val rowHeights = stripes.mapIndexed { index, stripe ->
            val maxAlignmentLine = alignmentLineSizes[index]
            stripe.maxOfOrNull {
                val placeable = placeables[it]
                val alignmentLine = alignmentLinePositions[it]
                if (alignmentLine == null) placeable.height else placeable.height + maxAlignmentLine - alignmentLine
            } ?: 0
        }
        val contentHeight = rowHeights.sum() + verticalSpacingPx * (stripes.size - 1)

        layout(
            constraints.constrainWidth(constraints.minWidth),
            constraints.constrainHeight(contentHeight)
        ) {
            val verticalPositions = IntArray(stripes.size)
            with(verticalArrangement) {
                arrange(contentHeight, rowHeights.toIntArray(), verticalPositions)
            }
            stripes.forEachIndexed { vindex, stripe ->
                val horizontalPositions = IntArray(stripe.size)
                with(horizontalArrangement) {
                    arrange(constraints.minWidth, stripe.map { placeables[it].width }.toIntArray(), layoutDirection, horizontalPositions)
                }
                stripe.forEachIndexed { hindex, it ->
                    val placeable = placeables[it]
                    val alignmentLine = alignmentLinePositions[it]
                    val alignmentLineShift = if (alignmentLine == null) 0 else alignmentLineSizes[vindex] - alignmentLine
                    placeable.place(horizontalPositions[hindex], verticalPositions[vindex] + alignmentLineShift)
                }
            }
        }
    }
}

@LayoutScopeMarker
@Immutable
interface FlowScope {
    @Stable
    fun Modifier.alignBy(alignmentLine: HorizontalAlignmentLine): Modifier

    @Stable
    fun Modifier.alignByBaseline(): Modifier
}

internal object FlowScopeInstance : FlowScope {
    @Stable
    override fun Modifier.alignBy(alignmentLine: HorizontalAlignmentLine) = this.then(
        WithAlignmentLine(
            alignmentLine = alignmentLine,
            inspectorInfo = debugInspectorInfo {
                name = "alignBy"
                value = alignmentLine
            }
        )
    )

    @Stable
    override fun Modifier.alignByBaseline() = alignBy(FirstBaseline)
}

internal class FlowParentData {
    var alignmentLine: AlignmentLine? = null
}

internal class WithAlignmentLine(
    val alignmentLine: AlignmentLine,
    inspectorInfo: InspectorInfo.() -> Unit
) : ParentDataModifier, InspectorValueInfo(inspectorInfo) {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return ((parentData as? FlowParentData) ?: FlowParentData()).also {
            it.alignmentLine = alignmentLine
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? WithAlignmentLine ?: return false
        return alignmentLine == otherModifier.alignmentLine
    }

    override fun hashCode(): Int = alignmentLine.hashCode()

    override fun toString(): String = "WithAlignmentLine(line=$alignmentLine)"
}
