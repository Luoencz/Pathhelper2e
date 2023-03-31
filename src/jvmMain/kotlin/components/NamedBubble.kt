package components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import data.*

@Composable
fun<T: NamedObject> NamedBubble(trait: NamedObject, modifier: Modifier, traitsList: SnapshotStateList<T>, content: @Composable (NamedObject) -> Unit) {
    Row(modifier = modifier) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        Box(
            Modifier
                .border(1.dp, Color.Black, RoundedCornerShape(10))
                .wrapContentWidth()
                .height(48.dp)
                .padding(3.dp)
                .hoverable(interactionSource = interactionSource),
            contentAlignment = Alignment.Center
        ) {
            content(trait)
            if (isHovered) Button(
                content = { Text(text = "x") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = { traitsList.remove(trait) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(15.dp)

            )
        }
    }
}