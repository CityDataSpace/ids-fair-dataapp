package de.fraunhofer.fit.IDSFAIRApp.model.data;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="InputData", description="FAIR validator data model")
public class FAIRvalidatorInputModel {


        
    public FAIRvalidatorInputModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String pid;
    private String liscence;
    private String publisher;
    private String title;
    private String publicationDate;
    private String modifiedDate;
    private String description;
    private String[] keywords;
    private String dataLink;
    private String dataAccessLevel;
    private String relatedResource;
    private String fileFormat;
    private String language;
    private String contactPoint;
    private int byteSize;
    private String version;
    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLiscence() {
        return liscence;
    }

    public void setLiscence(String liscence) {
        this.liscence = liscence;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getDataLink() {
        return dataLink;
    }

    public void setDataLink(String dataLink) {
        this.dataLink = dataLink;
    }

    public String getDataAccessLevel() {
        return dataAccessLevel;
    }

    public void setDataAccessLevel(String dataAccessLevel) {
        this.dataAccessLevel = dataAccessLevel;
    }

    public String getRelatedResource() {
        return relatedResource;
    }

    public void setRelatedResource(String relatedResource) {
        this.relatedResource = relatedResource;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContactPoint() {
        return contactPoint;
    }

    public void setContactPoint(String contactPoint) {
        this.contactPoint = contactPoint;
    }

    public int getByteSize() {
        return byteSize;
    }

    public void setByteSize(int byteSize) {
        this.byteSize = byteSize;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
