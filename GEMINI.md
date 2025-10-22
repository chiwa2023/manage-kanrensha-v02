# @GEMINI.md

# コード変更を適用する前に、フロントエンドのコード検証を実行する
gemini.hooks.pre-apply:
  - command: npm run check
    directory: app/front
    
# GitHub Issueに質問を投稿するためのカスタムフック
   6 gemini.hooks.ask-question:
   7   - command: >
   8       gh issue comment "$GEMINI_ISSUE_URL" --body "$GEMINI_QUESTION"
   9     description: GitHub Issueに質問を投稿します。
