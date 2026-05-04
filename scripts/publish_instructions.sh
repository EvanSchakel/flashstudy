#!/usr/bin/env bash
set -euo pipefail

echo "Repository already created at: https://github.com/EvanSchakel/flashstudy"
echo "To create a new GitHub repo from a local repo using gh (interactive), run:"
echo "  gh repo create REPO_NAME --public --source=. --remote=origin --push --description \"Description here\""
echo "To push changes:"
echo "  git add . && git commit -m 'message' && git push origin main"
echo "To create a release:"
echo "  git tag -a v0.1.0 -m 'Initial release' && git push origin --tags && gh release create v0.1.0 -t 'v0.1.0' -n 'Initial release'"
