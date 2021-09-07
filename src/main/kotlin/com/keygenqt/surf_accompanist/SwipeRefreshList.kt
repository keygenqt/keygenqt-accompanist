package com.keygenqt.surf_accompanist

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.keygenqt.modifier.visible

const val TAG = "SwipeRefreshList"

/**
 * Swipe Refresh + LazyColumn + Callbacks
 *
 * @since 0.0.1
 * @author Vitaliy Zarubin
 */
@Composable
fun <T : Any> SwipeRefreshList(
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    paddingBottom: Dp = 0.dp,
    items: LazyPagingItems<T>,
    state: SwipeRefreshState,
    contentLoadState: @Composable ((LoadState) -> Unit)? = null,
    contentLoading: @Composable (() -> Unit)? = null,
    contentEmpty: @Composable (() -> Unit)? = null,
    content: @Composable (Int, T) -> Unit,
) {
    SwipeRefresh(
        state = state,
        onRefresh = {
            items.refresh()
        },
        indicator = { st, tr ->
            SwipeRefreshIndicator(
                state = st,
                refreshTriggerDistance = tr,
                contentColor = MaterialTheme.colors.onPrimary,
            )
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if (items.itemCount != 0) {
            LazyColumn(
                contentPadding = PaddingValues(start = padding, top = padding, end = padding, bottom = paddingBottom),
                modifier = Modifier
                    .fillMaxSize()
                    .visible(items.loadState.refresh !is LoadState.Loading)
            ) {
                itemsIndexed(items) { index, item ->
                    item?.let {
                        content.invoke(index, item)
                    }
                }
                items.apply {
                    when {
                        this.loadState.append is LoadState.Loading -> {
                            item {
                                contentLoadState?.let {
                                    contentLoadState.invoke(LoadState.Loading)
                                } ?: run {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                        this.loadState.refresh is LoadState.Error -> {
                            val error = items.loadState.refresh as? LoadState.Error
                            error?.let {
                                item {
                                    contentLoadState?.let {
                                        contentLoadState.invoke(error)
                                    } ?: run {
                                        Log.e(TAG, "Refresh error: ${error.error.localizedMessage}")
                                    }
                                }
                            }
                        }
                        this.loadState.append is LoadState.Error -> {
                            val error = items.loadState.append as? LoadState.Error
                            error?.let {
                                item {
                                    contentLoadState?.let {
                                        contentLoadState.invoke(error)
                                    } ?: run {
                                        Log.e(TAG, "Append error: ${error.error.localizedMessage}")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) { }
        }
    }

    if (items.loadState.mediator != null) {
        if (items.itemCount == 0
            && items.loadState.refresh !is LoadState.Loading
            && items.loadState.prepend !is LoadState.Loading
        ) {
            contentEmpty?.invoke()
        }
        if (items.loadState.refresh is LoadState.Loading) {
            contentLoading?.invoke()
        }
    }
}