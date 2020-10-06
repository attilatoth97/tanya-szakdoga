export interface DevelopmentLogCreateDTO {
    projectId: number;
    taskId: number;
    description: string;
    developedHours: number;
    day: Date;
}
