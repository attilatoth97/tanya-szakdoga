import { TaskMiniDTO } from './task.mini.dto';

export interface SprintDTO {
    id: number;
    sprintName: string;
    dateOfStart: Date;
    dateOfEnd: Date;
    tasks: TaskMiniDTO[];
}
