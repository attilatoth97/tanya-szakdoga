export interface CommentDTO {
    id: number;
    text: string;
    userFullName: string;
    createDate: Date;
    own: boolean;
}
