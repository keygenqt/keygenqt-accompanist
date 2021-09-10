### SwipeRefreshList

```kotlin
/**
 * Swipe Refresh + LazyColumn + Callbacks
 *
 * @param modifier Modifier to apply to this layout node.
 * @param items List items.
 * @param state rememberSwipeRefreshState.
 * @param contentPadding a padding around the whole content.
 * @param contentLoadState loadState  LoadState.Loading / LoadState.Error.
 * @param contentLoading Content screen LoadState.Loading.
 * @param contentEmpty Content screen empty data.
 * @param content Content item model.
 **
 * @since 0.0.3
 */
@Composable
fun <T : Any> SwipeRefreshList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<T>,
    state: SwipeRefreshState = rememberSwipeRefreshState(items.loadState.refresh is LoadState.Loading),
    contentPadding: PaddingValues = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 0.dp),
    contentLoadState: @Composable ((LoadState) -> Unit)? = null,
    contentLoading: @Composable (() -> Unit)? = null,
    contentEmpty: @Composable (() -> Unit)? = null,
    content: @Composable (Int, T) -> Unit,
)
```