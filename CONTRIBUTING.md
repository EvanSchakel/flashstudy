# Contributing to FlashStudy

First off, thank you for considering contributing to FlashStudy! It's people like you that make FlashStudy a better tool for everyone.

## Where do I go from here?

If you've noticed a bug or have a feature request, please make one! We have templates for issues to help you get started.

## Fork & create a branch

If this is something you think you can fix, then fork FlashStudy and create a branch with a descriptive name.

## Get the test suite running

Make sure you have Java 11+ and Maven installed.
Run `mvn test` to ensure everything is working before you start making changes.

## Implement your fix or feature

At this point, you're ready to make your changes! Feel free to ask for help; everyone is a beginner at first :smile_cat:

## Make a Pull Request

At this point, you should switch back to your master branch and make sure it's up to date with FlashStudy's master branch:

```sh
git remote add upstream https://github.com/EvanSchakel/flashstudy.git
git checkout master
git pull upstream master
```

Then update your feature branch from your local copy of master, and push it!

```sh
git checkout 325-add-new-feature
git rebase master
git push --set-upstream origin 325-add-new-feature
```

Finally, go to GitHub and make a Pull Request! We have a template for this to help you describe your changes.

## Adding new Decks

If you have a great deck of flashcards you'd like to share with the community, you can add it to the `examples/` directory! Just make sure it's in the correct JSON format.
