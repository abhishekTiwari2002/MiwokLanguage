package com.example.miwoklanguage;

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int imageId=NO_IMAGE_PROVIDED;
    private int audioId;
   private static int NO_IMAGE_PROVIDED=-1;
     public Word(String defaultTranslation,String miwokTranslation,int imageId,int audioId){
         this.defaultTranslation=defaultTranslation;
         this.miwokTranslation=miwokTranslation;
         this.imageId=imageId;
         this.audioId=audioId;

     }
     public Word(String defaultTranslation,String miwokTranslation,int audioId){
         this.defaultTranslation=defaultTranslation;
         this.miwokTranslation=miwokTranslation;
         this.audioId=audioId;
     }

     public String getDefaultTranslation(){
         return defaultTranslation;
     }
     public String getMiwokTranslation(){
         return miwokTranslation;
     }
     public int getImageId(){return imageId; }
     public int getAudioId(){return audioId;}
    public boolean hasImage() {

        return imageId != NO_IMAGE_PROVIDED;
    }

}
