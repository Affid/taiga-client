package org.affid.taiga;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.affid.taiga.Utils.getStringFromParams;

public class UserStories {
    public static class Filter {
        private String project;
        private String milestone;
        private Boolean milestoneIsNull;
        private String status;
        private Boolean statusIsArchived;
        private String[] tags;
        private Integer[] watchers;
        private Integer assignedTo;
        private Integer epic;
        private Integer role;
        private Boolean statusIsClosed;
        private Integer excludeStatus;
        private String[] excludeTags;
        private Integer excludeAssignedTo;
        private Integer excludeRole;
        private Integer excludeEpic;

        public Filter project(String project) {
            this.project = project;
            return this;
        }

        public Filter milestone(String milestone) {
            this.milestone = milestone;
            return this;
        }

        public Filter status(String status) {
            this.status = status;
            return this;
        }

        public Filter milestoneIsNull(boolean isNull) {
            this.milestoneIsNull = isNull;
            return this;
        }

        public Filter statusIsArchived(boolean statusIsArchived) {
            this.statusIsArchived = statusIsArchived;
            return this;
        }

        public Filter tags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public Filter watchers(Integer[] watchers) {
            this.watchers = watchers;
            return this;
        }

        public Filter assignedTo(int assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Filter epic(int epic) {
            this.epic = epic;
            return this;
        }

        public Filter role(int role) {
            this.role = role;
            return this;
        }

        public Filter statusIsClosed(boolean statusIsClosed) {
            this.statusIsClosed = statusIsClosed;
            return this;
        }

        public Filter excludeStatus(int excludeStatus) {
            this.excludeStatus = excludeStatus;
            return this;
        }

        public Filter excludeTags(String[] excludeTags) {
            this.excludeTags = excludeTags;
            return this;
        }

        public Filter excludeAssignedTo(int excludeAssignedTo) {
            this.excludeAssignedTo = excludeAssignedTo;
            return this;
        }

        public Filter excludeRole(int excludeRole) {
            this.excludeRole = excludeRole;
            return this;
        }

        public Filter excludeEpic(int excludeEpic) {
            this.excludeEpic = excludeEpic;
            return this;
        }

        public boolean isEmpty() {
            return project == null && milestone == null && milestoneIsNull == null
                    && status == null && statusIsArchived == null && tags == null && watchers == null
                    && assignedTo == null && statusIsClosed == null && excludeAssignedTo == null
                    && excludeEpic == null && excludeRole == null && excludeTags == null && epic == null
                    && excludeStatus == null && role == null;
        }

        @Override
        public String toString() {
            ArrayList<String> params = new ArrayList<>();
            if (!isEmpty()) {
                if (project != null)
                    params.add("project=" + project);
                if (milestone != null)
                    params.add("milestone=" + milestone);
                if (milestoneIsNull != null)
                    params.add("milestone__isnull=" + milestoneIsNull);
                if (status != null)
                    params.add("staus=" + status);
                if (statusIsArchived != null)
                    params.add("status__is_archived=" + statusIsArchived);
                if (tags != null) {
                    String tagsString = Arrays.toString(tags);
                    params.add("tags=" + tagsString.substring(1, tagsString.length() - 1)
                            .replaceAll(" ", ""));
                }
                if (watchers != null) {
                    String watchersString = Arrays.toString(watchers);
                    params.add("watchers=" + watchersString.substring(1, watchersString.length() - 1)
                            .replaceAll(" ", ""));
                }
                if (assignedTo != null)
                    params.add("assigned_to=" + assignedTo);
                if (epic != null)
                    params.add("epic=" + epic);
                if (role != null)
                    params.add("role=" + role);
                if (statusIsClosed != null)
                    params.add("status__is_closed=" + statusIsClosed);
                if (excludeStatus != null)
                    params.add("exclude_status=" + excludeStatus);
                if (excludeTags != null) {
                    String tagsString = Arrays.toString(excludeTags);
                    params.add("exclude_tags=" + tagsString.substring(1, tagsString.length() - 1)
                            .replaceAll(" ", ""));
                }
                if (excludeAssignedTo != null)
                    params.add("exclude_assigned_to=" + excludeAssignedTo);
                if(excludeRole != null)
                    params.add("exclude_role=" + excludeRole);
                if(excludeEpic != null)
                    params.add("exclude_epic=" + excludeEpic);
                return getStringFromParams(params);
            }
            return "";
        }
    }

    public static JSONArray list(String serverUrl, String authToken) throws IOException {
        return list(serverUrl, authToken, null);
    }

    public static JSONArray list(String serverUrl, String authToken, Filter filter) throws IOException {
        if (filter == null || filter.isEmpty()) {
            return Utils.getList(serverUrl, authToken, "api/v1/userstories");
        }
        else {
            return Utils.getList(serverUrl, authToken, "api/v1/userstories" + filter);
        }
    }
}
