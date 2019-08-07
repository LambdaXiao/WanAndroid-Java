package app.android.rxwanandroidjava.ui.home.adapter;

import android.content.Context;
import android.text.TextUtils;

import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.adapter.BaseRecyclerAdapter;
import app.android.rxwanandroidjava.common.adapter.RecyclerViewHolder;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleData;


/**
 * 描述：
 */
public class ArticleListAdapter extends BaseRecyclerAdapter<FeedArticleData> {

    public ArticleListAdapter(Context ctx, List<FeedArticleData> list) {
        super(ctx, list);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_search_pager;
    }

    @Override
    protected void bindData(RecyclerViewHolder helper, int position, FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.setText(R.id.item_search_pager_title, article.getTitle());
        }
//        if (article.isCollect() || isCollectPage) {
//            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
//        } else {
        helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
//        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.setText(R.id.item_search_pager_author, article.getAuthor());
        }
//        setTag(helper, article);
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
//            if (isCollectPage) {
//                helper.setText(R.id.item_search_pager_chapterName, article.getChapterName());
//            } else {
            helper.setText(R.id.item_search_pager_chapterName, classifyName);
//            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.setText(R.id.item_search_pager_niceDate, article.getNiceDate());
        }
//        if (isSearchPage) {
//            CardView cardView = helper.getView(R.id.item_search_pager_group);
//            cardView.setForeground(null);
//            if (isNightMode) {
//                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
//            } else {
//                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
//            }
//        }
    }
}