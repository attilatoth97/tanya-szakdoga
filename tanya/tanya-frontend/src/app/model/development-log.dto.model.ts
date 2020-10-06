export interface DevelopmentLogDTO {
    id: number;
    userId: number;
    userName: string;
    projectId: number;
    projectName: string;
    taskId: number;
    taskName: string;
    description: string;
    developedHours: number;
    day: Date;
}
