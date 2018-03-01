# GitScraper

This is an app that gets data from Github's public API. The app will consist of 4 screens: A login screen, a repos screen, a repo details screen, and a preferences screen. Here are the details in regards to the screens:

1) Login screen - You will be able to sign in or sign up. Upon a successful sign up or log in, the app should take you to the repos screen.

2) Repos screen - This screen should have a search bar and a repos list. You will query for data from this endpoint based on what the user enters in the search bar. The data you should display for each repo should be: Name of repo, repo owner, stars number. Note that tapping on a repo, should take you to the repo details screen for that particular tapped repo.

3) Repo details screen - This screen should display more data regarding the repo being displayed. You should display:  name of repo, repo owner, stars number and 3 other fields (pick any 3).

4) Settings screen - This screen should display two things: User currently logged in and a logout button that logs the user out and takes them to the login screen.

Techinical Stack:
1) Data Storage: SQLite.
2) Network Call: Retrofit.
3) UI/UX: Recylerview.
