class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String renderedDocument;

    public DocumentEditor(Document document , Persistence storage){
        this.document = document;
        this.storage = storage;
        renderedDocument = "";
    }

    public void addText(String data){
        document.addElement(new TextElement(data));
    }

    public void addImage(String imagePath){
        document.addElement(new ImageElement(imagePath));
    }

    public void addNewLine(){
        document.addElement(new NewLineElement());
    }

    public void addTabSpace(){
        document.addElement(new TabSpaceElement());
    }

    public String renderDocument(){
        if(renderedDocument.isEmpty()){
            renderedDocument = document.render();
        }
        return renderedDocument;
    }

    public void saveDocument(){
        storage.save(renderDocument());
    }

}