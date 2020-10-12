import { OnInit, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentDTO } from 'src/app/model/comment.dto.modal';
import { CommentEditerDTO } from 'src/app/model/comment.editer.dto.modal';
import { TaskDTO } from 'src/app/model/task.dto.modal';
import { CommentService } from 'src/app/service/comment.service';
import { TaskService } from 'src/app/service/task.service';

@Component({
    selector: 'app-task-view-component',
    templateUrl: './task-view.component.html',
    styleUrls: ['./task-view.component.css'],
    providers: [TaskService, CommentService]

})
export class TaskViewComponent implements OnInit {

    currentTask = {} as TaskDTO;
    comments = [] as CommentDTO[];

    private readonly taskModifyUrl = 'task-update';
    public commentDescription = null as string;

    constructor(private taskService: TaskService,
        private commentService: CommentService,
        private activeRoute: ActivatedRoute,
        private route: Router) { }

    ngOnInit(): void {
        const currentTaskId = parseInt(this.activeRoute.snapshot.params['id'], 10);
        this.taskService.getTask(currentTaskId).subscribe(task => {
            this.currentTask = task;
            this.fetchComments(task.id);
        });
    }

    navigateToModify() {
        this.route.navigate(['/', this.taskModifyUrl, this.currentTask.id]);
    }

    fetchComments(taskId: number) {
        this.commentService.getCommentInTask(taskId).subscribe(comments => {
            this.comments = comments;
        });
    }

    sendComment() {
        if (this.commentDescription && this.commentDescription !== '') {
            const comment = {} as CommentEditerDTO;
            comment.taskId = this.currentTask.id;
            comment.text = this.commentDescription;
            this.commentService.create(comment).subscribe(() => {
                this.fetchComments(this.currentTask.id);
                this.commentDescription = null;
            });
        }
    }

    delete(id: number) {
        this.commentService.delete(id).subscribe(() => {
            this.fetchComments(this.currentTask.id);
        });
    }

}
