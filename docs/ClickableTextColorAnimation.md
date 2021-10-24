Clickable text with color animation on click

```kotlin
/**
 * @param modifier Modifier to apply to this layout node.
 * @param colorDefault Color for default state.
 * @param colorAction Color for animate.
 * @param text Text value.
 * @param delay Delay animation time.
 * @param style Text style.
 * @param underline Text style underline.
 * @param softWrap Whether the text should break at soft line breaks.
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if necessary.
 * @param overflow How visual overflow should be handled.
 * @param onTextLayout How visual overflow should be handled.
 * @param onClick Callback that is executed when users click the text. This callback is called
 * with clicked character's offset.
 */
@Composable
fun ClickableTextColorAnimation(
    modifier: Modifier = Modifier,
    colorDefault: Color,
    colorAction: Color,
    text: String,
    delay: Long = 500,
    style: TextStyle = TextStyle.Default,
    underline: Boolean = true,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: () -> Unit,
)
```