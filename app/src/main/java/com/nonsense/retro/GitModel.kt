package com.nonsense.retro

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GitModel (@Expose @SerializedName("login") var login:String?,
                     @Expose @SerializedName("since") var since: Int?,
                     @Expose @SerializedName("id") var nodeId:String?,
                     @Expose @SerializedName("avatar_url") var avatarUrl:String?,
                     @Expose @SerializedName("gravatar_id") var gravatarId:String?,
                     @Expose @SerializedName("url")  var url:String?,
                     @Expose @SerializedName("html_url") var htmlUrl:String?,
                     @Expose @SerializedName("followers_url")  var followersUrl:String?,
                     @Expose @SerializedName("following_url") var followingUrl:String?,
                     @Expose @SerializedName("gists_url") var gistsUrl:String?,
                     @Expose @SerializedName("starred_url") var starredUrl:String?,
                     @Expose @SerializedName("subscriptions_url")  var subscriptionsUrl:String?,
                     @Expose @SerializedName("organizations_url") var organizationsUrl:String?,
                     @Expose @SerializedName("repos_url")  var reposUrl:String?,
                     @Expose @SerializedName("events_url") var eventsUrl:String?,
                     @Expose @SerializedName("received_events_url") var receivedEventsUrl:String?,
                     @Expose @SerializedName("type")  var type:String?,
                     @Expose @SerializedName("site_admin") var siteAdmin:Boolean?)
