package com.harry.offerwallapp.adapter;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public abstract class RecyclerViewPaginator extends RecyclerView.OnScrollListener {
    /**
     * Keep track of current page.
     */
    private int currentPage = 1;

    /**
     * Use to set the threshold.
     */
    private final Integer threshold = 2;

    /**
     * App should notify only once to fetch more data.
     */
    private boolean endWithAuto = false;

    /**
     * Pass the RecyclerView in the constructor of the class
     * to get Layout Manager.
     */
    private RecyclerView.LayoutManager layoutManager;

    public static boolean isLoading = true;
    private int prevTotal = 0;

    public RecyclerViewPaginator(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this);
        this.layoutManager = recyclerView.getLayoutManager();
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == SCROLL_STATE_IDLE) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = 0;

            if(layoutManager instanceof LinearLayoutManager) {
                firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            if(isLastPage()) return;

            if ((visibleItemCount + firstVisibleItemPosition + threshold) >= totalItemCount) {
                if(!endWithAuto) {
                    endWithAuto = true;
                    currentPage = currentPage + 1;
                    loadMore(currentPage);
                }
            } else {
                endWithAuto = false;
            }
        }
    }

@Override
public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

}
    public abstract boolean isLastPage();
    public abstract void loadMore(int currentPage);
}
