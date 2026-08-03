param(
    [switch]$Push
)

$ErrorActionPreference = 'Stop'

# Git's HTTPS helper cannot handle this worktree's long metadata path. The
# primary checkout has the same object database at a short path, so use it
# for network operations while keeping this worktree as the working copy.
$worktree = Split-Path -Parent $PSScriptRoot
$commonGit = git -C $worktree rev-parse --git-common-dir
$primary = Split-Path -Parent $commonGit
$head = git -C $worktree rev-parse HEAD
$upstream = git -C $worktree rev-parse --abbrev-ref --symbolic-full-name '@{upstream}'

if ($upstream -notmatch '^([^/]+)/(.+)$') {
    throw "No remote upstream is configured for this branch."
}

$remote = $Matches[1]
$remoteBranch = $Matches[2]

git -C $primary fetch --prune $remote

if ($Push) {
    git -C $primary push $remote "$head`:$remoteBranch"
}

git -C $worktree status -sb
