LazyColumn with embedded SwipeRefresh. and set of states for content: Error, Empty, Loading

```kotlin
/**
 * @param modifier Modifier to apply to this layout node.
 * @param items List items.
 * @param listState rememberLazyListState.
 * @param refreshState rememberSwipeRefreshState.
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
    listState: LazyListState = rememberLazyListState(),
    refreshState: SwipeRefreshState = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading),
    indicator: @Composable (state: SwipeRefreshState, refreshTrigger: Dp) -> Unit,
    contentPadding: PaddingValues,
    contentLoadState: @Composable ((LoadState) -> Unit)? = null,
    contentLoading: @Composable (() -> Unit)? = null,
    contentEmpty: @Composable (() -> Unit)? = null,
    contentError: @Composable (() -> Unit)? = null,
    content: @Composable (Int, T) -> Unit,
)
```