# Stage 1: Build the app
FROM node:18-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy package.json and yarn.lock or package-lock.json to the container
COPY package.json ./

# Install dependencies
RUN yarn install --frozen-lockfile

# Copy the rest of the application source code to the container
COPY . .

# Build the Next.js application
RUN yarn build

# Stage 2: Create the production image
FROM node:18-alpine AS runner

# Set the working directory
WORKDIR /app

# Copy the built application from the builder stage
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/package.json ./

# Expose the port the app runs on
EXPOSE 3000

# Set environment variables
ENV NODE_ENV=production

# Command to run the application
CMD ["yarn", "start"]