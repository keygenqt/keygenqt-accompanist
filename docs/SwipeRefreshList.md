LazyColumn with embedded SwipeRefresh. and set of states for content: Error, Empty, Loading

```kotlin
/**
 * LazyColumn with embedded SwipeRefresh and set of states for content: Error, Empty, Loading
 *
 * @param modifier Modifier to apply to this layout node.
 * @param items List items.
 * @param indicator the indicator that represents the current state. By default this will use a [SwipeRefreshIndicator].
 * @param contentPadding a padding around the whole content.
 * @param contentLoadState loadState  LoadState.Loading / LoadState.Error.
 * @param contentLoading Content screen LoadState.Loading.
 * @param contentEmpty Content screen empty data.
 * @param content Content item model.
 */
@Composable
fun <T : Any> SwipeRefreshList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    listState: LazyListState,
    refreshState: SwipeRefreshState,
    indicator: @Composable (state: SwipeRefreshState, refreshTrigger: Dp),
    contentPadding: PaddingValues,
    contentLoadState: @Composable ((LoadState) -> Unit)?,
    contentLoading: @Composable (() -> Unit)?,
    contentEmpty: @Composable (() -> Unit)?,
    contentError: @Composable (() -> Unit)?,
    content: @Composable (Int, T) -> Unit,
)
```