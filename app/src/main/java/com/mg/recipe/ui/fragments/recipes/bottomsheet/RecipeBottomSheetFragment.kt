package com.mg.recipe.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mg.recipe.databinding.FragmentRecipesBottomSheetBinding
import com.mg.recipe.ui.fragments.recipes.RecipesViewModel
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_DIET_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_MEAL_TYPE
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale.ROOT

@AndroidEntryPoint
class RecipeBottomSheetFragment : BottomSheetDialogFragment() {

    private val recipesViewModel: RecipesViewModel by viewModels()

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    private var _binding: FragmentRecipesBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBottomSheetBinding.inflate(inflater, container, false).apply {
            mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val selectedMealType = chip.text.toString().toLowerCase(ROOT)
                mealTypeChip = selectedMealType
                mealTypeChipId = selectedChipId
            }

            dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val selectedDietType = chip.text.toString().toLowerCase(ROOT)
                dietTypeChip = selectedDietType
                dietTypeChipId = selectedChipId
            }

            applyBtn.setOnClickListener {
                recipesViewModel.saveMealAndDietType(
                    mealTypeChip,
                    mealTypeChipId,
                    dietTypeChip,
                    dietTypeChipId
                )
                val action =
                    RecipeBottomSheetFragmentDirections.actionRecipeBottomSheetFragmentToReceipesFragment(
                        true
                    )
                findNavController().navigate(action)
            }
        }

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                Log.d("RecipesBottomSheet", e.message.toString())
            }
        }
    }
}