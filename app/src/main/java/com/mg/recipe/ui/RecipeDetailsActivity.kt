package com.mg.recipe.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mg.recipe.R
import com.mg.recipe.databinding.ActivityRecipeDetailsBinding
import com.mg.recipe.repo.local.entities.Favorite
import com.mg.recipe.ui.fragments.MainViewModel
import com.mg.recipe.ui.fragments.recipes.details.adapters.PagerAdapter
import com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.IngredientsFragment
import com.mg.recipe.ui.fragments.recipes.details.fragments.instructions.InstructionsFragment
import com.mg.recipe.ui.fragments.recipes.details.fragments.overview.OverviewFragment
import dagger.hilt.android.AndroidEntryPoint

const val KEY_RECIPE_BUNDLE = "recipeBundle"

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

    private val args by navArgs<RecipeDetailsActivityArgs>()

    private val mainViewModel: MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0L

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViewPager()
    }

    private fun initViewPager() {
        val fragments = arrayListOf(
            OverviewFragment(),
            IngredientsFragment(),
            InstructionsFragment()
        )
        val titles = arrayListOf(
            getString(R.string.overview),
            getString(R.string.ingredients),
            getString(R.string.instructions)
        )

        val bundle = Bundle().apply {
            putSerializable(KEY_RECIPE_BUNDLE, args.result)
        }

        val pagerAdapter = PagerAdapter(bundle, fragments, titles, supportFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.recipe_details_menu, menu)
        menu?.findItem(R.id.save_to_favorites)?.let {
            checkSavedRecipes(it)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites && !recipeSaved) {
            saveToFavorites(item)
        } else if (item.itemId == R.id.save_to_favorites && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(menuItem: MenuItem) {
        val entity = Favorite(result = args.result)
        mainViewModel.insertFavoriteRecipe(entity)
        changeMenuItemColor(menuItem, R.color.yellow)
        showSnackBar(R.string.recipe_saved)
    }

    private fun removeFromFavorites(item: MenuItem) {
        val entity = Favorite(savedRecipeId, args.result)
        mainViewModel.deleteFavoriteRecipe(entity)
        changeMenuItemColor(item, R.color.white)
        showSnackBar(R.string.removed_from_favorites)
        recipeSaved = false
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavorite.observe(this, {
            try {
                for (savedRecipe in it) {
                    if (savedRecipe.result.id == args.result.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                    } else {
                        changeMenuItemColor(menuItem, R.color.white)
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun changeMenuItemColor(menuItem: MenuItem, color: Int) {
        menuItem.icon.setTint(ContextCompat.getColor(this, color))
    }

    private fun showSnackBar(message: Int) {
        Snackbar.make(binding.detailsLayout, getString(message), Snackbar.LENGTH_SHORT)
            .setAction(getString(R.string.ok)) {}
            .show()
    }
}