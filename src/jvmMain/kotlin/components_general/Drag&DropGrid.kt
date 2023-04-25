package components_general

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.pow


fun <T> MutableList<T>.move(fromIdx: Int, toIdx: Int) {
    val tmp = this[fromIdx]
    this[fromIdx] = this[toIdx]
    this[toIdx] = tmp

    /*if (toIdx > fromIdx) {
        for (i in fromIdx until toIdx) {
            this[i] = this[i + 1].also { this[i + 1] = this[i] }
        }
    } else {
        for (i in fromIdx downTo toIdx + 1) {
            this[i] = this[i - 1].also { this[i - 1] = this[i] }
        }
    }*/
}

@Composable
fun<T> dragAndDropGrid(contentsData: SnapshotStateList<T>, contentsGenerator: @Composable (T) -> Unit ) {
    val lazyGridState = rememberLazyGridState()

    var position by remember {
        mutableStateOf<Pair<Float,Float>?>(null)
    }

    var draggedItem by remember {
        mutableStateOf<Int?>(null)
    }
    var changeItem by remember {
        mutableStateOf<Int?>(null)
    }

    draggedItem = when {
        changeItem == null -> null
        draggedItem == null -> changeItem
        else -> changeItem.also { contentsData.move(draggedItem!!, it!!) }
    }

    val indexWithOffset by derivedStateOf {
        draggedItem
            ?.let { lazyGridState.layoutInfo.visibleItemsInfo.getOrNull(it - lazyGridState.firstVisibleItemIndex) }
            ?.let { Triple(it.index, (position?.first ?: 0f) - it.offset.x - it.size.width / 2f,(position?.second ?: 0f) - it.offset.y - it.size.height / 2f) }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .width(1230.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        lazyGridState.layoutInfo.visibleItemsInfo
                            .find { item ->
                                offset.x.toInt() in item.offset.x..(item.offset.x + item.size.width) && offset.y.toInt() in item.offset.y..(item.offset.y + item.size.height)
                            }
                            ?.also { item ->
                                position =
                                    Pair(item.offset.x + item.size.width / 2f, item.offset.y + item.size.height / 2f)
                            }
                    },
                    onDrag = { change, offset ->
                        change.consume()
                        position = Pair(
                            position?.first?.plus(offset.x) ?: offset.x,
                            position?.second?.plus(offset.y) ?: offset.y
                        )
                        changeItem = lazyGridState.layoutInfo.visibleItemsInfo.minByOrNull {
                            ((position!!.first.minus((it.offset.x + it.size.width / 2f)))
                                .toDouble()
                                .pow(2.0)
                                    +
                                    (position!!.second.minus((it.offset.y + it.size.height / 2f)))
                                        .toDouble()
                                        .pow(2.0)
                                    ).pow(0.5)
                        }?.index
                    },
                    onDragEnd = {
                        position = null
                        changeItem = null
                        draggedItem = null
                    }
                )
            },
        state = lazyGridState
    ) {
        //, {index, it -> it.hashCode()} - fix Drag&Drop selection
        this.itemsIndexed(contentsData) { index, it ->
                val offset_x by remember {
                    derivedStateOf { indexWithOffset?.takeIf { it.first == index }?.second }
                }
                val offset_y by remember {
                    derivedStateOf { indexWithOffset?.takeIf { it.first == index }?.third }
                }
                Box(
                    Modifier
                        .zIndex(offset_x?.let { 1f } ?: 0f)
                        .graphicsLayer { translationX = offset_x ?: 0f }
                        .graphicsLayer { translationY = offset_y ?: 0f }) {
                    contentsGenerator(it)
            }
        }
    }
}