import { IssueType } from './enum/issue-type.enum';
import { IssueStatus } from './enum/issue-status.enum';

export interface TaskEditerDTO {
    issueName: string;
    description: string;
    responsibleUserId: number;
    issueStatus: IssueStatus;
    issueType: IssueType;
    sprintId: number;
    projectId: number;
    estimatedTime: number;
}

