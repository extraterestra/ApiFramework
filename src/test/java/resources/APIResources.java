package resources;

public enum APIResources {
    getLatestRatings("/api/latest"),
    getInvalidLatestRatings("/api/"),
    getRatingsApi("/api");

    private String resource;

    APIResources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }

}
