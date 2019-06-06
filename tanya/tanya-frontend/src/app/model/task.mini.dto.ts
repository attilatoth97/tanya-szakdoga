import { IssueType } from './enum/issue-type.enum';
import { IssueStatus } from './enum/issue-status.enum';

export interface TaskMiniDTO {
    id: number;
    issueName: string;
    createUserName: string;
    responsibleUserName: string;
    issueStatus: IssueStatus;
    issueType: IssueType;
}

