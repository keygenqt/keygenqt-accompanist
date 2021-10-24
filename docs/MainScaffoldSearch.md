The main block of the application with topBar which has a built-in search and loader

```kotlin
/**
 * @param modifier Modifier to apply to this layout node.
 * @param contentTitle Content rendered in the topBar body
 * @param contentLoad Content loader (default it CircularProgressIndicator) rendered in the topBar body
 * @param contentLoadState Loader state, true is enable false is disable
 * @param navigationIcon Navigation icon nullable
 * @param navigationIconDescription Navigation icon description
 * @param navigationIconOnClick Navigation icon callback click
 * @param searchIcon Search icon, default - Icons.Default.Search
 * @param searchIconDescription Search icon description
 * @param searchListener Callback text for search
 * @param closeSearchListener Callback close search enable
 * @param searchTextColor Color text
 * @param topBarIconColor Color icons
 * @param topBarBackgroundColor Color bg TobBar
 * @param searchDescription Hint show after click search icon
 * @param topBarElevation the elevation of this TopAppBar.
 * @param actions for add custom IconButton-s
 * @param content main body content
 */
@Composable
fun MainScaffoldSearch(
    modifier: Modifier = Modifier,
    contentTitle: @Composable (() -> Unit)? = null,
    contentLoad: @Composable (() -> Unit)? = null,
    contentLoadState: Boolean = false,
    navigationIcon: ImageVector? = null,
    navigationIconDescription: String = "Navigate up",
    navigationIconOnClick: () -> Unit = {},
    searchIcon: ImageVector = Icons.Default.Search,
    searchIconDescription: String = "Search",
    searchListener: ((String?) -> Unit)? = null,
    closeSearchListener: (() -> Unit)? = null,
    searchTextColor: Color = MaterialTheme.colors.onPrimary,
    topBarIconColor: Color = MaterialTheme.colors.onPrimary,
    topBarBackgroundColor: Color = MaterialTheme.colors.primary,
    searchDescription: String = "Search...",
    topBarElevation: Dp = AppBarDefaults.TopAppBarElevation,
    actions: @Composable ((RowScope) -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
)
```