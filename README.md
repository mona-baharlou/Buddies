# Buddies App 

# SignUpScreenTest README

## Overview
`SignUpScreenTest` is a UI test class designed to verify the signup functionality of an Android application using Jetpack Compose. The tests ensure that users can enter their email and password and successfully navigate to the timeline screen upon submitting the signup form. This class leverages Koin for dependency injection and Compose testing libraries for UI interactions.

## Code Breakdown

### Class Declaration

```kotlin
class SignUpScreenTest {
```
This defines the `SignUpScreenTest` class which will contain all the test cases related to the signup screen.

### Test Rule

```kotlin
@get:Rule
val signupTestRule = createAndroidComposeRule<MainActivity>()
```
A test rule is declared which initializes an `AndroidComposeTestRule`. This rule is crucial for launching the `MainActivity` and controlling the testing environment.

### Dependency Setup

```kotlin
private val offLineUser = OffLineUser()
```
An instance of `OffLineUser` is created. This object simulates a user when the application is not connected to a network.

```kotlin
private val signUpModule = module {
    factory<UserCatalog> { offLineUser } //replacement for offlineUser that is created in application Module
}
```
This code sets up a Koin module where `UserCatalog` is provided with the instance of `offLineUser`. This allows for dependency injection in tests, ensuring you are testing with the desired user catalog implementation.

### Test Setup

```kotlin
@Before
fun setUp() {
    loadKoinModules(signUpModule)
}
```
The `setUp` method runs before each test. It loads the `signUpModule` into Koin, ensuring that the dependencies are correctly set up prior to running any test cases.

### Test Method

```kotlin
@Test
fun performSignUp() {
```
This annotation indicates that the `performSignUp` function is a test case.

#### Launching the Signup Screen

```kotlin
launchSignUpScreen(signupTestRule) {
    typeEmail("monaa@gmail.com")
    typePassword("paSsword1@")
    submit()
} verify {
    timeLineScreenIsPresented()
}
```
- **`launchSignUpScreen`:** A method that sets up the signup screen for user interaction.
- **`typeEmail` and `typePassword`:** These methods simulate typing the user's email and password into their respective text fields.
- **`submit()`:** This method simulates the action of clicking the signup button.
- **`verify`:** This block checks if the timeline screen is displayed after the signup process.

### Launching Signup Screen Function

```kotlin
fun launchSignUpScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: SignUpRobot.() -> Unit,
): SignUpRobot {
    return SignUpRobot(rule).apply(block)
}
```
This function launches the signup screen and allows for the execution of a block of code within the context of a `SignUpRobot`. The `SignUpRobot` acts as a helper class for performing actions on the signup screen.

### SignUpRobot Class

```kotlin
class SignUpRobot(
    val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {
```
The `SignUpRobot` class encapsulates methods to interact with the signup screen.

#### Input Methods

```kotlin
fun typeEmail(email: String) {
    val emailHint = rule.activity.getString(R.string.email)
    rule.onNodeWithText(emailHint)
        .performTextInput(email)
}
```
This method finds the email input field and simulates typing the provided email.

```kotlin
fun typePassword(password: String) {
    val passwordHint = rule.activity.getString(R.string.password)
    rule.onNodeWithText(passwordHint)
        .performTextInput(password)
}
```
Similar to `typeEmail`, this method finds the password input field and simulates typing in the provided password.

#### Submit Method

```kotlin
fun submit() {
    val signUp = rule.activity.getString(R.string.signup)
    rule.onNodeWithText(signUp)
        .performClick()
}
```
This method performs a click action on the signup button.

### Verification Class

```kotlin
infix fun verify(
    block: SignUpVerification.() -> Unit,
): SignUpVerification {
    return SignUpVerification(rule).apply(block)
}
```
This allows for verification checks after performing actions on the signup screen using the `SignUpVerification` class.

### SignUpVerification Class

```kotlin
class SignUpVerification(
    private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {
```
The `SignUpVerification` class is responsible for verifying the outcomes after actions are performed on the signup screen.

#### Verification Method

```kotlin
fun timeLineScreenIsPresented() {
    val timeline = rule.activity.getString(R.string.timeline)

    rule.onNodeWithText(timeline)
        .assertIsDisplayed()
}
```
This method checks whether the timeline screen is displayed by asserting that the corresponding text is visible.

## Conclusion
The `SignUpScreenTest` class provides a structured approach to verifying the signup functionality within an Android app using UI testing frameworks. The combination of Koin for dependency injection and Compose testing libraries enables straightforward testing and validation of user interactions within the application's UI.
