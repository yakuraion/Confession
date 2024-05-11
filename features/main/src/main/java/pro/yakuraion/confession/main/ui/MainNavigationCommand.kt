package pro.yakuraion.confession.main.ui

sealed class MainNavigationCommand {

    data object NavigateToStacks : MainNavigationCommand()
}
