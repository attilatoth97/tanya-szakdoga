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

    @Input() comment: CommentDTO = null;
    @Output() deleteEmitter: EventEmitter<number>;

    delete(id: number) {
        this.deleteEmitter.emit(id);
    }

}
