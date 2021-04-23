package com.mg.recipe.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.mg.recipe.R
import com.mg.recipe.databinding.ActivityRecipeDetailsBinding
import com.mg.recipe.ui.fragments.recipes.details.adapters.PagerAdapter
import com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.IngredientsFragment
import com.mg.recipe.ui.fragments.recipes.details.fragments.instructions.InstructionsFragment
import com.mg.recipe.ui.fragments.recipes.details.fragments.overview.OverviewFragment

const val KEY_RECIPE_BUNDLE = "recipeBundle"

class RecipeDetailsActivity : AppCompatActivity() {

    private val args by navArgs<RecipeDetailsActivityArgs>()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}