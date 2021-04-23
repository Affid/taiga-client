package org.affid.taiga;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.affid.taiga.Utils.getStringFromParams;

@SuppressWarnings("unused")
public class Projects {
    public enum Order {
        memberships__user_order,
        total_fans,
        total_fans_last_week,
        total_fans_last_month,
        total_fans_last_year,
        total_activity,
        total_activity_last_week,
        total_activity_last_month,
        total_activity_last_year
    }

    public static class Filter {
        private Integer member;
        private ArrayList<Integer> members;
        private Boolean isLookingForPeople;
        private Boolean isFeatured;
        private Boolean isBacklogActivated;
        private Boolean isKanbanActivated;
        private Order orderedBy;

        public Filter member(int mem) {
            this.member = mem;
            return this;
        }

        public Filter orderedBy(Order order) {
            this.orderedBy = order;
            return this;
        }

        public Filter members(ArrayList<Integer> mems) {
            this.members = new ArrayList<>();
            this.members.addAll(mems);
            return this;
        }

        public Filter isLookingForPeople(boolean isLooking) {
            this.isLookingForPeople = isLooking;
            return this;
        }

        public Filter isFeatured(boolean isFeat) {
            this.isFeatured = isFeat;
            return this;
        }

        public Filter isBacklogActivated(boolean isActivated) {
            this.isBacklogActivated = isActivated;
            return this;
        }

        public Filter isKanbanActivated(boolean isKan) {
            this.isKanbanActivated = isKan;
            return this;
        }

        public String toString() {
            ArrayList<String> params = new ArrayList<>();
            if (member != null) {
                params.add("member=" + member);
            }
            if (members != null && member == null) {
                params.add("members=" + new JSONArray(this.members));
            }
            if (isLookingForPeople != null) {
                params.add("&is_looking_for_people=" + isLookingForPeople);
            }
            if (isFeatured != null) {
                params.add("&is_featured=" + isFeatured);
            }
            if (isBacklogActivated != null) {
                params.add("&is_backlog_activated=" + isBacklogActivated);
            }
            if (isKanbanActivated != null) {
                params.add("&is_kanban_activated=" + isKanbanActivated);
            }
            if (orderedBy != null) {
                params.add("order_by=" + orderedBy.name());
            }
            return getStringFromParams(params);
        }

        public boolean isEmpty() {
            return member == null && members == null && isLookingForPeople == null && isFeatured == null && isBacklogActivated == null && isKanbanActivated == null && orderedBy == null;
        }
    }

    public static void main(String[] args) {
        Filter filter = new Filter().orderedBy(Order.total_fans).member(30);
        System.out.println(filter.toString());
    }

    public static JSONArray list(String serverUrl, String authToken) throws IOException {
        return list(serverUrl, authToken, null);
    }

    public static JSONArray list(String serverUrl, String authToken, Filter filter) throws IOException {
        if (filter == null || filter.isEmpty())
            return Utils.getList(serverUrl, authToken, "api/v1/projects");
        else
            return Utils.getList(serverUrl, authToken, "api/v1/projects" + filter);
    }

    public static JSONObject create(String serverUrl, String authToken, JSONObject body) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        Utils.sendRequest(con, body);

        return Utils.getResponse(con);
    }

    public static boolean delete(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "DELETE");

        return con.getResponseCode() == 204;
    }

    public static JSONObject get(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static JSONObject getBySlug(String serverUrl, String authToken, String slug) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/by_slug?slug=" + slug);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static JSONObject edit(String serverUrl, String authToken, String id, JSONObject body) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id);
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        Utils.sendRequest(con, body);

        return Utils.getResponse(con);
    }

    public static boolean bulkUpdateOrder(String serverUrl, String authToken, JSONObject body) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/bulk_update_order");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        Utils.sendRequest(con, body);

        return con.getResponseCode() == 204;
    }

    public static JSONObject getModulesConfiguration(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/modules");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static boolean editModulesConfiguration(String serverUrl, String authToken, String id, JSONObject body) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/modules");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        Utils.sendRequest(con, body);

        return con.getResponseCode() == 204;
    }

    public static JSONObject stats(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/stats");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static JSONObject issueStats(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/issues_stats");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static JSONObject tagColors(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/tags_colors");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "GET");

        return Utils.getResponse(con);
    }

    public static boolean createTag(String serverUrl, String authToken, String id, String tag) throws IOException {
        return createTag(serverUrl, authToken, id, tag, null);
    }

    public static boolean createTag(String serverUrl, String authToken, String id, String tag, String color) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/create_tag");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        JSONObject request = new JSONObject().put("tag", tag);
        if (color != null)
            request.put("color", color);
        Utils.sendRequest(con, request);

        return con.getResponseCode() == 200;
    }

    public static boolean editTag(String serverUrl, String authToken, String id, String oldTag, String newTag) throws IOException {
        return editTag(serverUrl, authToken, id, oldTag, newTag, null);
    }

    public static boolean editTag(String serverUrl, String authToken, String id, String oldTag, String newTag, String color) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/edit_tag");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        JSONObject request = new JSONObject().put("from_tag", oldTag).put("to_tag", newTag);
        if (color != null)
            request.put("color", color);

        Utils.sendRequest(con, request);

        return con.getResponseCode() == 200;
    }

    public static boolean deleteTag(String serverUrl, String authToken, String id, String tag) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/delete_tag");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        Utils.sendRequest(con, new JSONObject().put("tag", tag));

        return con.getResponseCode() == 200;
    }

    public static boolean mixTags(String serverUrl, String authToken, String id, ArrayList<String> fromTags, String toTag) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/mix_tags");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");
        JSONObject request = new JSONObject().put("to_tag", toTag).put("from_tags", fromTags);
        Utils.sendRequest(con, request);

        return con.getResponseCode() == 200;
    }

    public static boolean likeProject(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/like");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        return con.getResponseCode() == 200;
    }

    public static boolean unlikeProject(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/unlike");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        return con.getResponseCode() == 200;
    }

    public static JSONArray listProjectFans(String serverUrl, String authToken, String id) throws IOException {
        return Utils.getList(serverUrl, authToken, "api/v1/projects/" + id + "/fans");
    }

    public static boolean watchProject(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/watch");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        return con.getResponseCode() == 200;
    }

    public static boolean stopWatchingProject(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/unwatch");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        return con.getResponseCode() == 200;
    }

    public static JSONArray listProjectWatchers(String serverUrl, String authToken, String id) throws IOException {
        return Utils.getList(serverUrl, authToken, "api/v1/projects/" + id + "/watchers");
    }

    public static JSONObject createTemplate(String serverUrl, String adminAuthToken, String id, String name, String desription) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/create_template");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, adminAuthToken, "POST");
        JSONObject request = new JSONObject().put("template_name", name).put("template_description", desription);
        Utils.sendRequest(con, request);

        return Utils.getResponse(con);
    }

    public static boolean leave(String serverUrl, String authToken, String id) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/leave");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        Utils.configure(con, authToken, "POST");

        return con.getResponseCode() == 200;
    }

    public static JSONObject changeLogo(String serverUrl, String authToken, String id, File logo) throws IOException {
        URL url = new URL(serverUrl + "api/v1/projects/" + id + "/change_logo");
        HttpURLConnection con = Utils.getConnection(url, serverUrl.contains("https"));

        con.setRequestProperty("Authorization", "Bearer " + authToken);
        MultipartUtility multipart = new MultipartUtility(con, "UTF-8");

        multipart.addFilePart("logo", logo);

        List<String> response = multipart.finish();

        System.out.println("SERVER REPLIED:");

        for (String line : response) {
            System.out.println(line);
        }

        return Utils.getResponse(multipart.getHttpConn());
    }
}
