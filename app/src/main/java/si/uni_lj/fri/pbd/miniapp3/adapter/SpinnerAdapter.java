package si.uni_lj.fri.pbd.miniapp3.adapter;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import si.uni_lj.fri.pbd.miniapp3.R;
import si.uni_lj.fri.pbd.miniapp3.databinding.SpinnerItemBinding;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientDTO;

public class SpinnerAdapter extends BaseAdapter {

    private List<IngredientDTO> mIngredients = null;

    public void setIngredients(List<IngredientDTO> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public int getCount() {
        if (mIngredients != null) {
            return mIngredients.size();
        }
        return 0;
    }

    @Override
    public IngredientDTO getItem(int position) {
        if (mIngredients != null) {
            return mIngredients.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerItemBinding binding;
        if (convertView == null) {
            // Inflate new view and bind it
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            binding = SpinnerItemBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
        }
        else {
            // View already exists and is bound, get binding for view
            binding = DataBindingUtil.getBinding(convertView);
        }

        IngredientDTO current = getItem(position);
        if (binding != null) {
            binding.setIngredient(current);
        }

        return convertView;
    }
}
