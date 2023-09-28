package com.itc.teamsmarties.ui.signup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val signInViewModel = SignupViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val navController = rememberNavController()

            SignupScreen(
                navController,
                signInViewModel
            )
        }
    }

    @Test
    fun signupScreenButtonNotEnabled() {
        val usernameNode = composeTestRule
            .onNodeWithTag("User Name")
            .assertIsDisplayed()

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val confirmpassword = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()

        val signUpNode = composeTestRule.onNodeWithTag("confirm")

        usernameNode.performTextInput("invalidName")
        signUpNode.assertIsNotEnabled()

        emailNode.performTextInput("invalidEmail")
        signUpNode.assertIsNotEnabled()

        passwordNode.performTextInput("12345678")
        signUpNode.assertIsNotEnabled()

        confirmpassword.performTextInput("12345678")
        signUpNode.assertIsNotEnabled()
    }

    @Test
    fun signupScreenButtonEnabled() {
        val usernameNode = composeTestRule
            .onNodeWithTag("User Name")
            .assertIsDisplayed()

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val confirmpassword = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()

        val signUpNode = composeTestRule.onNodeWithTag("confirm")

        usernameNode.performTextInput("testworld")
        signUpNode.assertIsNotEnabled()

        emailNode.performTextInput("valid@email")
        passwordNode.performTextInput("1234567")
        confirmpassword.performTextInput("1234567")
        signUpNode.assertIsNotEnabled()

        emailNode.performTextInput(".com")
        signUpNode.assertIsEnabled()
    }


    @Test
    fun validateInvalidUserName() {
        val usernameNode = composeTestRule
            .onNodeWithTag("User Name")
            .assertIsDisplayed()
        usernameNode.performTextInput("123")

        val usernameErrorNode = composeTestRule
            .onNodeWithTag("username_error")
            .assertIsDisplayed()

        usernameErrorNode.assertTextEquals("User Name should be more than 5 chars")

    }

    @Test
    fun validateValidUserName() {
        val usernameNode = composeTestRule
            .onNodeWithTag("User Name")
            .assertIsDisplayed()
        usernameNode.performTextInput("signup")

        val usernameErrorNode = composeTestRule
            .onNodeWithTag("username_error")
        usernameErrorNode.assertTextEquals("")
    }


    @Test
    fun validateInvalidPassword() {
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        // Password is less than 6 characters
        passwordNode.performTextInput("abc12")

        val passwordErrorNode = composeTestRule
            .onNodeWithTag("password_error")
            .assertIsDisplayed()

        passwordErrorNode.assertTextEquals("Password should be greater than 6")
    }



    @Test
    fun validateValidPassword(){
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        // Input a valid password (length greater than 6 characters)
        passwordNode.performTextInput("res1238")

        // Wait for the composition to be updated
        composeTestRule.waitForIdle()

         composeTestRule
            .onNodeWithTag("password_error")
            .assertIsNotDisplayed()
    }



    @Test
    fun validateInvalidConfirmPassword() {
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()
        passwordNode.performTextInput("res123")

        val confirmPasswordNode = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()
        confirmPasswordNode.performTextInput("wrong123")

        val confirmPasswordErrorNode = composeTestRule
            .onNodeWithTag("confirm password_error")
            .assertIsDisplayed()

        confirmPasswordErrorNode.assertTextEquals("Password did not match")
    }


    @Test
    fun validateValidConfirmPassword(){
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()
        passwordNode.performTextInput("res1235")
        val confirmPasswordNode = composeTestRule
            .onNodeWithTag("confirm password")
            .assertIsDisplayed()
        confirmPasswordNode.performTextInput("res1235")

        composeTestRule.waitForIdle()
        composeTestRule
            .onNodeWithTag("confirm password_error")
            .assertIsNotDisplayed()


    }
}