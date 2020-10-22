package si.uni_lj.fri.pbd.miniapp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import si.uni_lj.fri.pbd.miniapp3.databinding.LayoutGridItemBinding;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeSummaryIM;
import si.uni_lj.fri.pbd.miniapp3.ui.details.DetailsActivity;
import si.uni_lj.fri.pbd.miniapp3.ui.details.DetailsViewModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {

    private List<RecipeSummaryIM> mRecipeSummaries;
    private String mInstantiatedFrom;

    public RecyclerViewAdapter(String instantiatedFrom) {
        mInstantiatedFrom = instantiatedFrom;
    }

    public void setRecipeSummaries(List<RecipeSummaryIM> recipeSummaries) {
        mRecipeSummaries = recipeSummaries;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutGridItemBinding binding = LayoutGridItemBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (mRecipeSummaries != null) {
            RecipeSummaryIM recipe = mRecipeSummaries.get(position);
            holder.bind(recipe);

            // Set a on click listener for thumbnail pictures
            holder.imageView.setOnClickListener(v -> {
                String id = recipe.getIdMeal();
                Context context = v.getContext();
                Intent detailsActivity = new Intent(context, DetailsActivity.class);
                detailsActivity.putExtra(DetailsViewModel.RECIPE_ID, id);
                detailsActivity.putExtra(DetailsViewModel.INSTANTIATED_FROM, mInstantiatedFrom);
                context.startActivity(detailsActivity);
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mRecipeSummaries != null) {
            return mRecipeSummaries.size();
        }
        return 0;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class CardViewHolder extends RecyclerView.ViewHolder {

        private LayoutGridItemBinding mBinding;
        View imageView;

        CardViewHolder(LayoutGridItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            this.imageView = mBinding.imageRecipeThumbnail;
        }

        // This method is used to bind instance of RecipeSummaryIM to row
        void bind(RecipeSummaryIM rec) {
            mBinding.setRecipe(rec);
            mBinding.executePendingBindings();
        }
    }
}
