import { OnInit, Component, Input } from '@angular/core';
import { CommentDTO } from 'src/app/model/comment.dto.modal';

@Component({
    selector: 'app-comment-view-component',
    templateUrl: './comment-view.component.html',
    styleUrls: ['./comment-view.component.css'],
    providers: []

})

export class CommentViewComponent {

    @Input() comment: CommentDTO = null;

}
