package com.fitness.assistant.ui.fragments.home


import android.animation.ObjectAnimator
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.fitness.assistant.R
import com.fitness.assistant.data.models.user.UserGoal
import com.fitness.assistant.data.models.user.UserLeftValues
import com.fitness.assistant.databinding.FragmentHomeUpMenuBinding
import com.fitness.assistant.util.GoogleFit
import com.fitness.assistant.viewModels.HomeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home_up_menu.*
import java.security.Permissions
import javax.inject.Inject
import kotlin.math.roundToInt

class HomeUpFragment @Inject constructor() : DaggerFragment() {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private val ANIMATION_DURATION = 300L
    private lateinit var binding: FragmentHomeUpMenuBinding
    var googleFit: GoogleFit? = null
    var permissions: Permissions? = null
    var userAccount: GoogleSignInAccount? = null
    private val GOOGLE_SIGN_IN_CODE = 313
    private val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 345
    lateinit var userValues: UserLeftValues
    lateinit var burnCalories: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = activity?.run {
            ViewModelProvider(this, modelFactory).get(HomeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_up_menu, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setObservers()

        googleSignInRequest()

        return binding.root
    }


    private fun setObservers() {
        // when parameter changes - animate progress bar
        viewModel.userLeftValues.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            userValues = it
            val caloriesProgress = it.calories.third
            val fatsProgress = it.fatsLeft.third
            val proteinsProgress = it.proteinsLeft.third
            val carbsProgress = it.carbsLeft.third

            ObjectAnimator.ofInt(binding.fragmentHomeUpPBar, "progress", caloriesProgress)
                .setDuration(ANIMATION_DURATION)
                .start()

            ObjectAnimator.ofInt(binding.upMenuProteinsBar, "progress", proteinsProgress)
                .setDuration(ANIMATION_DURATION)
                .start()

            ObjectAnimator.ofInt(binding.upMenuCarbsBar, "progress", carbsProgress)
                .setDuration(ANIMATION_DURATION)
                .start()

            ObjectAnimator.ofInt(binding.upMenuFatsBar, "progress", fatsProgress)
                .setDuration(ANIMATION_DURATION)
                .start()

        })


        val burnedCaloriesInfo: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val info = intent.extras
                if (info!!.isEmpty) {
                } else {
                    val burnedCalories: String? = info.getString("burnedCalories")
                    if (burnedCalories != null) {
                        // ADD burned calories from total calories
                        if (viewModel.user.value!!.goal == UserGoal.GAIN) {
                            if (up_menu_calories_text != null) {
                                up_menu_calories_text.text =
                                    (viewModel.userLeftValues.value!!.calories.first + (burnedCalories.toDouble()
                                        .roundToInt())).toString()
                            }
                        }
                        burnCalories = burnedCalories
                        viewModel.updateBurnedCalories(
                            burnedCalories.toDouble().roundToInt().toString()
                        )
                    }
                }
            }
        }
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(
            burnedCaloriesInfo, IntentFilter("burnedCalories")
        )

    }


    private fun requestRestrictedPermissions() {
        if (!GoogleSignIn.hasPermissions(
                userAccount,
                GoogleFit.getInstance(context).fitnessOptions
            )
        ) {
            GoogleSignIn.requestPermissions(
                this,  // your activity
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,  // e.g. 1
                userAccount, GoogleFit.getInstance(context).fitnessOptions
            )
        } else {
            GoogleFit.getInstance(context).sendRequest()
        }
    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            userAccount = completedTask.getResult(ApiException::class.java)
            requestRestrictedPermissions()
        } catch (e: ApiException) {
            googleSignInRequest()
        }
    }

    fun googleSignInRequest() {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), signInOptions)
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, GOOGLE_SIGN_IN_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GOOGLE_SIGN_IN_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                GoogleFit.getInstance(context).sendRequest()
            } else {
                requestRestrictedPermissions()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}

