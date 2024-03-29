package com.codingforanimals.animalacademy.presenter.recipes.recipes.single.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.recipes.recipes.RecipesContract
import com.codingforanimals.animalacademy.model.data.models.recipes.SingleRecipe
import com.codingforanimals.animalacademy.presenter.recipes.recipes.single.SingleRecipePresenter
import kotlinx.android.synthetic.main.recipes_child_single.view.*

class SingleRecipeAdapter(
    options: FirestorePagingOptions<SingleRecipe>,
    iRecipesView: RecipesContract.View
) : FirestorePagingAdapter<SingleRecipe, SingleRecipeViewHolder>(options) {

    private val presenter = SingleRecipePresenter(iRecipesView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleRecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recipes_child_single, parent, false)
        return SingleRecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: SingleRecipeViewHolder,
        position: Int,
        recipe: SingleRecipe
    ) {
        presenter.handleSingleRecipeLoading(recipe, holder)

        holder.itemView.likes.setOnClickListener { presenter.onLikesButtonClick(recipe, holder) }
        holder.itemView.src.transitionName = recipe.id
        holder.itemView.src.setOnClickListener {
            presenter.onSingleRecipeClick(recipe, holder.itemView.src.drawable, holder.itemView.src)
        }
    }
}