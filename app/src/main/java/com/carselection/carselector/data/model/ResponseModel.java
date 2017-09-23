package com.carselection.carselector.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "pageSize",
        "totalPageCount",
        "wkda"
})
public class ResponseModel implements Parcelable {

    @JsonProperty("page")
    private Integer page;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("totalPageCount")
    private Integer totalPageCount;
    @JsonProperty("wkda")
    private Map<String, String> wkda;

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("totalPageCount")
    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    @JsonProperty("totalPageCount")
    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @JsonProperty("Wkda")
    public Map<String, String> getWkda() {
        return wkda;
    }

    @JsonProperty("Wkda")
    public void setWkda(Map<String, String> wkda) {
        this.wkda = wkda;
    }

    public void addWkda(Map<String, String> wkda) { this.wkda.putAll(wkda);}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.page);
        dest.writeValue(this.pageSize);
        dest.writeValue(this.totalPageCount);
        dest.writeInt(this.wkda.size());
        for (Map.Entry<String, String> entry : this.wkda.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public ResponseModel() {
    }

    protected ResponseModel(Parcel in) {
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageSize = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPageCount = (Integer) in.readValue(Integer.class.getClassLoader());
        int wkdaSize = in.readInt();
        this.wkda = new HashMap<String, String>(wkdaSize);
        for (int i = 0; i < wkdaSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.wkda.put(key, value);
        }
    }

    public static final Parcelable.Creator<ResponseModel> CREATOR = new Parcelable.Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel source) {
            return new ResponseModel(source);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };
}
