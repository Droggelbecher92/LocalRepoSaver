export interface GithubUser {
    login : string,
    avatar_url: string,
    repos_url: string,
    public_repos: number,
}

export interface GithubRepo {
    id : number,
    name: string,
    html_url: string,
}