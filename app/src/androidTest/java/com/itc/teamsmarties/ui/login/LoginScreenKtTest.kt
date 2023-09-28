package com.itc.teamsmarties.ui.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoginScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    val loginViewModel = LoginViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            LoginScreen(loginViewModel)
        }
    }

    @Test
    fun validateValidEmailFormat() {
        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()
        emailNode.performTextInput("test@example.com")

        composeTestRule
            .onNodeWithTag("email_error")
            .assertIsNotDisplayed()
    }

    @Test
    fun validateInvalidEmailFormat(){
        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()
        emailNode.performTextInput("test")

        val emailErrorNode = composeTestRule
            .onNodeWithTag("email_error")
            .assertIsDisplayed()

        emailErrorNode.assertTextEquals("Input proper email id")

    }

    @Test
    fun validateInvalidPassword() {
        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        // Password is less than 6 characters
        passwordNode.performTextInput("abcd12")

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
        passwordNode.performTextInput("resh123")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("password_error")
            .assertIsNotDisplayed()
    }


    @Test
    fun loginScreenButtonNotEnabled() {
        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val loginNode = composeTestRule.onNodeWithTag("Login")

        emailNode.performTextInput("invalidEmail")
        loginNode.assertIsNotEnabled()

        passwordNode.performTextInput("1234")
        loginNode.assertIsNotEnabled()

    }

    @Test
    fun loginScreenButtonEnabled() {

        val emailNode = composeTestRule
            .onNodeWithTag("email")
            .assertIsDisplayed()

        val passwordNode = composeTestRule
            .onNodeWithTag("password")
            .assertIsDisplayed()

        val loginNode = composeTestRule.onNodeWithTag("Login")

        emailNode.performTextInput("valid@email")
        passwordNode.performTextInput("1234567")
        loginNode.assertIsNotEnabled()

        emailNode.performTextInput(".com")
        loginNode.assertIsEnabled()
    }

}