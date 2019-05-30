export interface TaskDTO {
    id: number;
    issueName: string;
    dateOfCreate: Date;
    createUserName: string;
    description: string;
    responsibleUserName: string;
    isClose: boolean;
    issueStatus: IssueStatus;
    issueType: IssueType;
    sprintId: number;
    dateOfLastRevisal: Date;
}

