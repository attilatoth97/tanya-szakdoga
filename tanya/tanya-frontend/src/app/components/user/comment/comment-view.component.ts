import { OnInit, Component, Input, Output, EventEmitter } from '@angular/core';
import { CommentDTO } from 'src/app/model/comment.dto.modal';
import { CommentService } from 'src/app/service/comment.service';

@Component({
    selector: 'app-comment-view-component',
    templateUrl: './comment-view.component.html',
    styleUrls: ['./comment-view.component.css'],
    providers: [CommentService]
})

export class CommentViewComponent {

    @Input() comment: CommentDTO;
    @Output() deleteEmitter = new EventEmitter<number>();

    delete() {
        this.deleteEmitter.emit(this.comment.id);
    }

}
