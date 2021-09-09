## Surf Accompanist

Accompanist is a group of libraries that aim to supplement [Jetpack Compose](https://developer.android.com/jetpack/compose) with features that are commonly required by developers but not yet available.

### Connection

```gradle
repositories {
    maven("https://artifactory.keygenqt.com/artifactory/open-source")
}
dependencies {
    implementation("com.keygenqt.accompanist:surf-accompanist:0.0.3")
}
```

#### Components

### MainScaffoldSearch
```kotlin
/**
 * Main for application body with searchable
 *
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
 *
 * @since 0.0.3
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

### ClickableTextColorAnimation
```kotlin
/**
 * Clickable text color animation
 *
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
 *
 * @since 0.0.3
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

# License

```
Copyright 2021 Vitaliy Zarubin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```