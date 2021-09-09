Surf Accompanist
===================

![picture](data/just-image.png)

#### Connection:

```gradle
repositories {
    maven("https://artifactory.keygenqt.com/artifactory/open-source")
}
dependencies {
    implementation("com.keygenqt.accompanist:surf-accompanist:0.0.3")
}
```

### Components

#### MainScaffoldSearch
```kotlin
/**
Main for application body with searchable
Params:
    modifier - Modifier to apply to this layout node.
    contentTitle - Content rendered in the topBar body
    contentLoad - Content loader (default it CircularProgressIndicator) rendered in the topBar body
    contentLoadState - Loader state, true is enable false is disable
    navigationIcon - Navigation icon nullable
    navigationIconDescription - Navigation icon description
    navigationIconOnClick - Navigation icon callback click
    searchIcon - Search icon, default - Icons.Default.Search
    searchIconDescription - Search icon description
    searchListener - Callback text for search
    closeSearchListener - Callback close search enable
    searchTextColor - Color text
    topBarIconColor - Color icons
    topBarBackgroundColor - Color bg TobBar
    searchDescription - Hint show after click search icon
    topBarElevation - the elevation of this TopAppBar.
    actions - for add custom IconButton-s
    content - main body content
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

#### SwipeRefreshList
```kotlin
/**
Swipe Refresh + LazyColumn + Callbacks
Params:
    modifier - Modifier to apply to this layout node.
    items - List items
    state - rememberSwipeRefreshState
    contentPadding - a padding around the whole content.
    contentLoadState - loadState LoadState.Loading / LoadState.Error
    contentLoading - Content screen LoadState.Loading
    contentEmpty - Content screen empty data
    content - Content item model
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

#### ClickableTextColorAnimation
```kotlin
/**
Clickable text color animation
Params:
    modifier - Modifier to apply to this layout node.
    colorDefault - Color for default state.
    colorAction - Color for animate.
    text - Text value.
    delay - Delay animation time.
    style - Text style.
    underline - Text style underline.
    softWrap - Whether the text should break at soft line breaks.
    maxLines - An optional maximum number of lines for the text to span, wrapping if necessary.
    overflow - How visual overflow should be handled.
    onTextLayout - How visual overflow should be handled.
    onClick - Callback that is executed when users click the text. This callback is called with clicked character's offset.
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