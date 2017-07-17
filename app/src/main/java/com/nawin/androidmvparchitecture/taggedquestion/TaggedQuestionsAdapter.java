package com.nawin.androidmvparchitecture.taggedquestion;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nawin.androidmvparchitecture.R;
import com.nawin.androidmvparchitecture.data.model.TaggedQuestions;
import com.nawin.androidmvparchitecture.views.LoadMoreAdapter;

import java.util.List;

/**
 * Created by brainovation on 6/14/17.
 */

public class TaggedQuestionsAdapter extends LoadMoreAdapter<TaggedQuestionsAdapter.TaggedQuestionsHolder> {
    private final List<TaggedQuestions> items;
    private final TaggedQuestionSelectionListener listener;

    public TaggedQuestionsAdapter(@NonNull RecyclerView recyclerView,
                                  boolean showLoading,
                                  List<TaggedQuestions> taggedQuestions,
                                  TaggedQuestionSelectionListener listener) {
        super(recyclerView, showLoading);
        this.items = taggedQuestions;
        this.listener = listener;
    }

    @Override
    public int getItemCount_() {
        return items.size();
    }

    @Override
    public TaggedQuestionsHolder onCreateViewHolder_(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new TaggedQuestionsHolder(inflater.inflate(R.layout.view_holder_tagged_question_items, parent, false));
    }

    @Override
    public void onBindViewHolder_(final TaggedQuestionsHolder holder, final int position) {

        TaggedQuestions taggedQuestions = items.get(position);
        if (taggedQuestions != null) {
            holder.title.setText(taggedQuestions.getTitle());
            holder.count.setText(taggedQuestions.getAnswerCount());
        }

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTaggedQuestionSelected(items.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemViewType_(int position) {
        return items.size();
    }

    public void addMoreItems(List<TaggedQuestions> items, boolean hasMoreItems) {
        final int count = this.items.size();
        this.items.addAll(items);
        onItemsAdded(count, items.size(), hasMoreItems);
    }

    interface TaggedQuestionSelectionListener {
        void onTaggedQuestionSelected(TaggedQuestions items);
    }

    static class TaggedQuestionsHolder extends RecyclerView.ViewHolder {
        private final TextView count, title;
        private final CardView rootView;

        TaggedQuestionsHolder(View itemView) {
            super(itemView);
            count = (TextView) itemView.findViewById(R.id.count);
            title = (TextView) itemView.findViewById(R.id.title);
            rootView = (CardView) itemView.findViewById(R.id.rootView);
        }
    }
}
