#!/bin/bash
original_date=$(git log -1 --format=%ad HEAD)
echo $original_date
GIT_COMMITTER_DATE="$original_date" git commit --amend --no-edit
git rebase --continue
