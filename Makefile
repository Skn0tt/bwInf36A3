%.pdf: %.md
	pandoc Dokumentation.md -o Dokumentation.pdf --filter mermaid-filter