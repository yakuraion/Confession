package pro.yakuraion.confession.main.ui

sealed class MainNavigationCommand {

    data object NavigateBack : MainNavigationCommand()

    data object NavigateToStacks : MainNavigationCommand()

    data object NavigateToNewConfession : MainNavigationCommand()

    data object NavigateToSins : MainNavigationCommand()
}
