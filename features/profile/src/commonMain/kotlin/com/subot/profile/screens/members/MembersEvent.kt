package com.subot.profile.screens.members

sealed class MembersEvent {
    data class SearchMembers(val query: String) : MembersEvent()
    data object RefreshMembers : MembersEvent()
    data object LoadMore : MembersEvent()
    data object ClearError : MembersEvent()
}
