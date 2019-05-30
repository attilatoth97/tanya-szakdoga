export interface TaskEditerDTO {
    id: number;
    issueName: string;
    description: string;
    responsibleUserId: number;
    issueStatus: IssueStatus;
    issueType: IssueType;
    sprintId: number;
    projectId: number;
    estimatedTime: number;
}

