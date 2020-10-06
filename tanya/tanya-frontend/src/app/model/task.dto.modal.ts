import { IssueType } from './enum/issue-type.enum';
import { IssueStatus } from './enum/issue-status.enum';

export interface TaskDTO {
    id: number;
    issueName: string;
    dateOfCreate: Date;
    createUserName: string;
    description: string;
    responsibleUserId: number;
    responsibleUserName: string;
    isClose: boolean;
    issueStatus: IssueStatus;
    issueType: IssueType;
    sprintId: number;
    sprintName: string;
    projectId: number;
    projectName: string;
    dateOfLastRevisal: Date;
    estimatedTime: number;
}

